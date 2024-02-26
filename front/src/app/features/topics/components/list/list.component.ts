import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { PostInformation } from '../../../../interfaces/postInformation.interface';
import { PostService } from '../../../../services/post.service';
import { Topic } from "../../../../interfaces/topic.interface";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TopicService} from "../../../../services/topic.service";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {

  public topics$: Observable<Topic[]> = this.topicService.all();

  constructor(
    private postService: PostService,
    private topicService: TopicService,
    private matSnackBar: MatSnackBar
  ) { }

  public subscribe(topicId: number): void {
    this.topicService.subscribe(topicId).subscribe(response => {
      this.matSnackBar.open(response.message, 'Close', { duration: 4000 });
    });
  }

  get user(): PostInformation | undefined {
    return this.postService.postInformation;
  }
}
