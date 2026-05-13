package uz.mahliyoedu.service;

import uz.mahliyoedu.dto.ApplicationRequestDto;
import uz.mahliyoedu.entity.Application;
import uz.mahliyoedu.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    // Constructor injection
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // Yangi so'rov saqlash — foydalanuvchi formani to'ldirganda
    public Application save(ApplicationRequestDto dto) {
        Application application = new Application();
        application.setName(dto.getName());
        application.setPhone(dto.getPhone());
        // createdAt va viewed Entity da @PrePersist orqali avtomatik o'rnatiladi
        return applicationRepository.save(application);
    }

    // Barcha so'rovlarni olish — admin uchun
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    // Faqat ko'rilmaganlarni olish — admin uchun
    public List<Application> getUnviewed() {
        return applicationRepository.findByViewedFalse();
    }

    // So'rovni ko'rilgan deb belgilash — admin ko'rganda
    public Application markAsViewed(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found: " + id));
        application.setViewed(true);
        return applicationRepository.save(application);
    }

    // So'rovni o'chirish — admin uchun
    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}
