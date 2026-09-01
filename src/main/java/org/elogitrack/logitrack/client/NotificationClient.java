package org.elogitrack.logitrack.client;

import org.elogitrack.logitrack.dto.NotificationRequestDTO;
import org.elogitrack.logitrack.dto.NotificationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "logitrack-notifications", url = "${notification.service.url:http://localhost:8082}")
public interface NotificationClient {

    @PostMapping("/api/notifications")
    ResponseEntity<NotificationResponseDTO> sendNotification(@RequestBody NotificationRequestDTO requestDTO);
}
