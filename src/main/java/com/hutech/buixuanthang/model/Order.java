package com.hutech.buixuanthang.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String phone;

    private String email;

    private String address;

    private String note;

    private String paymentMethod;

    private String status;

    private double totalAmount; // New field for total amount

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Thêm trường user để liên kết với người dùng đặt hàng
}