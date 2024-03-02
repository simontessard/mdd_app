import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './features/auth/services/auth.service';
import { PostService } from './services/post.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  constructor(
    private authService: AuthService,
    private router: Router,
    private postService: PostService) {
  }

  ngOnInit() {
    this.postService.checkAuthenticationStatus();
  }

  public $isLogged(): Observable<boolean> {
    return this.postService.$isLogged();
  }

  public isLoginOrRegister(): boolean {
    const route = this.router.url;
    return route === '/login' || route === '/register';
  }
}
