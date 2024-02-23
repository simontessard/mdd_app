import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { PostInformation } from '../../../../interfaces/postInformation.interface';
import { PostService } from '../../../../services/post.service';
import { TopicApiService } from '../../services/topic-api.service';
import { Topic } from "../../../../interfaces/topic.interface";
import {Sub} from "../../interfaces/sub.interface";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {

  public topics$: Observable<Topic[]> = this.topicApiService.all();

  constructor(
    private postService: PostService,
    private topicApiService: TopicApiService,
    private matSnackBar: MatSnackBar
  ) { }

  public subscribe(topicId: number): void {
    this.topicApiService.subscribe(topicId).subscribe(response => {
      this.matSnackBar.open(response.message, 'Close', { duration: 4000 });
    });
  }

  get user(): PostInformation | undefined {
    return this.postService.postInformation;
  }
}
