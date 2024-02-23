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

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(user: PostInformation): void {
    this.postInformation = user;
    this.isLogged = true;
    this.next();
    localStorage.setItem(this.TOKEN_KEY, user.token);
  }

  public logOut(): void {
    this.postInformation = undefined;
    this.isLogged = false;
    this.next();
    localStorage.removeItem(this.TOKEN_KEY);
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
