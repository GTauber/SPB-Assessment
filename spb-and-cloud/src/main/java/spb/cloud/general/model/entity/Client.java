package spb.cloud.general.model.entity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

@Data
@Builder
@JsonInclude(NON_NULL)
public class Client {

    @Id
    private Long id;
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();
    @Version
    private Long version;
    private String name;
    private String email;
    private String phoneNumber;
    @CreatedDate
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}