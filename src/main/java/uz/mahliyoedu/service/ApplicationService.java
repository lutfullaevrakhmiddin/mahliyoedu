package uz.mahliyoedu.service;

import uz.mahliyoedu.dto.ApplicationRequestDto;
import uz.mahliyoedu.entity.Application;
import uz.mahliyoedu.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application save(ApplicationRequestDto dto) {
        Application app = new Application();
        app.setName(dto.getName());
        app.setPhone(dto.getPhone());
        app.setCourse(dto.getCourse()); // yangi field
        return applicationRepository.save(app);
    }

    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    public List<Application> getUnviewed() {
        return applicationRepository.findByViewedFalse();
    }

    public Application markAsViewed(Long id) {
        Application app = applicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Topilmadi: " + id));
        app.setViewed(true);
        return applicationRepository.save(app);
    }

    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}
