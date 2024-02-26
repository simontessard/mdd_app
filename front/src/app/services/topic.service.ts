import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';
import {PostService} from "./post.service";

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private pathService = 'api/topics';
  private pathSub = 'api/subs';

  constructor(private httpClient: HttpClient, private postService: PostService) { }

  public all(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.pathService);
  }

  public subscribe(topicId: number) {
    const body = {
      userId: this.postService.postInformation?.id,
      topicId: topicId
    };
    return this.httpClient.post<any>(`${this.pathSub}`, body);
  }
}
