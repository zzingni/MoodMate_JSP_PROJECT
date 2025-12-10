package com.example.demo.member;

import java.time.LocalDateTime;

import com.example.demo.meeting.Meeting;
import com.example.demo.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "members")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private int memberId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "meeting_id", nullable = false)
	private Meeting meeting;
	
	@Column(name="confirm_status")
	private String confirmStatus;
	
	@Column(name="apply_date")
	private LocalDateTime applyDate;
	
	@PrePersist
    public void prePersist() {
        if (this.confirmStatus == null) {
            this.confirmStatus = "대기";
    }
	    if (this.applyDate == null) {
	        this.applyDate = LocalDateTime.now();
	    }
    }
	
}
