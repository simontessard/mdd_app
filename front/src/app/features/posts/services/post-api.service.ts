import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/post.interface';
import { Comment } from "../interfaces/comment.interface";

@Injectable({
  providedIn: 'root'
})
export class PostApiService {

  private pathService = 'api/posts';
  private pathComment = 'api/comments';

  constructor(private httpClient: HttpClient) {
  }

  public all(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.pathService);
  }

  public detail(id: string): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${id}`);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete(`${this.pathService}/${id}`);
  }

  public create(post: Post): Observable<Post> {
    return this.httpClient.post<Post>(this.pathService, post);
  }

  public update(id: string, post: Post): Observable<Post> {
    return this.httpClient.put<Post>(`${this.pathService}/${id}`, post);
  }

  public getComments(id: string): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(`${this.pathComment}/${id}`);
  }

  public addComment(id: string, userId: string): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/${id}/participate/${userId}`, null);
  }
}
