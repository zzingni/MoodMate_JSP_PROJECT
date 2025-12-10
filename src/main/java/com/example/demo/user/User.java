package com.example.demo.user;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.meeting.Meeting;
import com.example.demo.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="login_id", nullable = false, length = 50)
	private String loginId;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, length = 30)
	private String nickname;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false, length = 10)
	private String gender;
	
	@Column(nullable = false)
	private int age;
	
	@Column(nullable = false, length = 255)
	private String favorite;
	
	private String status;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@PrePersist
	    public void prePersist() {
	        if (this.status == null) {
	            this.status = "ACTIVE";
	    }
	    if (this.createdAt == null) {
	        this.createdAt = LocalDateTime.now();
	    }
	}
	
	@OneToMany(mappedBy = "user")
	private List<Meeting> meetings = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Member> members = new ArrayList<>();
}
