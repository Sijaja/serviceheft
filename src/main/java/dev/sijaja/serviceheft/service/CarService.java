package dev.sijaja.serviceheft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.sijaja.serviceheft.model.Cars;
import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.model.User;
import dev.sijaja.serviceheft.repository.CarRepository;
import dev.sijaja.serviceheft.repository.OwnerRepository;
import dev.sijaja.serviceheft.repository.UserRepository;

@Service
public class CarService {

    private final CarRepository repo;
    private final OwnerRepository ownerRepo;
    private final UserRepository userRepo;

    public CarService(CarRepository repo, OwnerRepository ownerRepo, UserRepository userRepo) {
        this.repo = repo;
        this.ownerRepo = ownerRepo;
        this.userRepo = userRepo;
    }

    public List<Cars> getAll() {
        return repo.findAll();
    }

    public Optional<Cars> get(int id) {
        return repo.findById(id);
    }

    public Cars save(Cars car) {
        return repo.save(car);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public List<Cars> findCarsForOwner(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        return repo.findAllByOwnerOwnerId(owner.getOwnerId());
    }

    public Optional<Cars> findCarForOwner(int carId, String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        return repo.findByCarIdAndOwnerOwnerId(carId, owner.getOwnerId());
    }

    public Cars getDefaultCarForOwner(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        int defaultCarId = owner.getDefaultCarId();
        return repo.findById(defaultCarId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Default car not found"));
    }

    public void setDefaultCar(String email, int carId) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Owner owner = ownerRepo.findByUserUserId(user.getUserId()).orElse(null);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Owner not found");
        }
        Cars car = repo.findById(carId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        if (car.getOwner().getOwnerId() != owner.getOwnerId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Car does not belong to the owner");
        }
        owner.setDefaultCarId(carId);
        ownerRepo.save(owner);
    }
}
