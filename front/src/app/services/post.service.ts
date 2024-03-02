import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PostInformation } from '../interfaces/postInformation.interface';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  public isLogged = false;
  public postInformation: PostInformation | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);
  private readonly TOKEN_KEY = 'jwtToken';
  private readonly USER_DATA_KEY = 'userData';

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(user: PostInformation): void {
    this.postInformation = user;
    this.isLogged = true;
    this.next();
    localStorage.setItem(this.TOKEN_KEY, user.token);
    localStorage.setItem(this.USER_DATA_KEY, JSON.stringify(user));
  }

  public logOut(): void {
    this.postInformation = undefined;
    this.isLogged = false;
    this.next();
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_DATA_KEY);
  }

  public checkAuthenticationStatus(): void {
    const token = localStorage.getItem(this.TOKEN_KEY);
    const userData = localStorage.getItem(this.USER_DATA_KEY);
    if (token && userData) {
      this.isLogged = true;
      this.isLoggedSubject.next(this.isLogged);
      this.postInformation = JSON.parse(userData);
    }
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
