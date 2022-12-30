package com.app.controller;

import com.app.MailRequest;


@RestController
@RequestMapping("/mail")
public class MailController {

    public ResponseEntity<MailRequest> sendEmail(@RequestBody MailRequest mailRequest) {
       System.out.println("Primera prueba "+ mailRequest.toString());
       return ResponseEntity.ok(mailRequest);
    }

    @Operation(summary = "prueba")
    @GetMapping()
    public ResponseEntity<String> findById() {
        return ResponseEntity.ok("ole!");
    }
}
