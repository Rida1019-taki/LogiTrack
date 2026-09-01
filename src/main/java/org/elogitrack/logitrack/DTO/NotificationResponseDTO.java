package org.elogitrack.logitrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elogitrack.logitrack.enums.NotificationType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDTO {
    private Long id;
    private String recipient;
    private String message;
    private NotificationType notificationType;
    private LocalDateTime createdAt;
    private boolean read;
    private Long orderId;
}
