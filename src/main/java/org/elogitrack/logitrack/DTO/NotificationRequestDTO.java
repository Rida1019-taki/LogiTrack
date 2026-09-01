package org.elogitrack.logitrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elogitrack.logitrack.enums.NotificationType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {
    private String recipient;
    private String message;
    private NotificationType notificationType;
    private Long orderId;
}
