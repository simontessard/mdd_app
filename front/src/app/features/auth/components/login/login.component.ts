import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PostInformation } from 'src/app/interfaces/postInformation.interface';
import { PostService } from 'src/app/services/post.service';
import { LoginRequest } from '../../interfaces/loginRequest.interface';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    email: [
      'simonard@gmail.com',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      'test1234!',
      [
        Validators.required,
        Validators.min(3)
      ]
    ]
  });

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private postService: PostService) {
  }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: PostInformation) => {
        this.postService.logIn(response);
        this.router.navigate(['/posts']);
      },
      error: error => this.onError = true,
    });
  }

  public back() {
    window.history.back();
  }
}
