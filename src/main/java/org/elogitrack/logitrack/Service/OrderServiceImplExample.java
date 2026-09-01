package org.elogitrack.logitrack.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elogitrack.logitrack.client.NotificationClient;
import org.elogitrack.logitrack.dto.NotificationRequestDTO;
import org.elogitrack.logitrack.enums.NotificationType;
import org.springframework.stereotype.Service;

/**
 * Example Service demonstrating how to inject and use the NotificationClient.
 * This can be adapted into your actual CommandeService / OrderService.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImplExample {

    private final NotificationClient notificationClient;

    public void createOrder(Long orderId, String customerEmail) {
        log.info("Creating order {} for customer {}", orderId, customerEmail);
        
        // 1. Order Creation Logic Here...

        // 2. Send Notification Synchronously
        try {
            NotificationRequestDTO notificationRequest = NotificationRequestDTO.builder()
                    .recipient(customerEmail)
                    .message("Your order #" + orderId + " has been successfully created.")
                    .notificationType(NotificationType.ORDER_CREATED)
                    .orderId(orderId)
                    .build();

            var response = notificationClient.sendNotification(notificationRequest);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Notification sent successfully! ID: {}", response.getBody().getId());
            } else {
                log.warn("Failed to send notification. Status: {}", response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Error occurred while sending notification to logitrack-notifications service", e);
        }
    }
}
