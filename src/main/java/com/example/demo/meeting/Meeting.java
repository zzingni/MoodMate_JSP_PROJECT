package com.example.demo.meeting;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.Length;

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
@Table(name = "meetings")
public class Meeting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="meeting_id")
	private int meetingId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	@Column(name="meeting_image_url")
	private String imageUrl;
	
	@Column(name="meeting_date", nullable = false)
	private Date meetingDate;
	
	@Column(length = 255, nullable = false)
	private String location;
	
	@Column(nullable = false)
	private int member;
	
	@Column(length = 255, nullable = false)
	private String category;
	
	@Column(name = "content_name", length = 100, nullable = false)
	private String contentName;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "meeting_status", length = 10)
	private String meetingStatus;
	
	@PrePersist
    public void prePersist() {
        if (this.meetingStatus == null) {
            this.meetingStatus = "진행";
    }
    if (this.createdAt == null) {
        this.createdAt = LocalDateTime.now();
    }
}
}
