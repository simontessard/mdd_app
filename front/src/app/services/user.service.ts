import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';
import {Sub} from "../features/topics/interfaces/sub.interface";
import {Subcription} from "../interfaces/sub.interface";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathService = 'api/user';
  private pathSub = 'api/subs';

  constructor(private httpClient: HttpClient) { }

  public getById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }

  public getSubscriptions(id: string): Observable<Subcription[]> {
    return this.httpClient.get<Subcription[]>(`${this.pathSub}/${id}`);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete(`${this.pathService}/${id}`);
  }
}
