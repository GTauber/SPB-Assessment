package spb.cloud.general.model.dto;

import spb.cloud.general.model.entity.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link Client}
 */
public record ClientDto(Long id, String uuid,
                        @Size(min = 3, max = 15)
                        @NotBlank(message = "Name is required")
                        String name,
                        @Email(message = "Email should be valid")
                        @NotBlank(message = "Email is required")
                        String email,
                        @NotBlank(message = "Phone number is required")
                        String phoneNumber) implements
    Serializable {

}