import { Component } from '@angular/core';
import {map, Observable} from 'rxjs';
import { PostInformation } from '../../../../interfaces/postInformation.interface';
import { PostService } from '../../../../services/post.service';
import { Post } from '../../interfaces/post.interface';
import { PostApiService } from '../../services/post-api.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {

  public posts$: Observable<Post[]> = this.postApiService.all();
  public sortAscending = true;

  constructor(
    private postService: PostService,
    private postApiService: PostApiService
  ) { }

  get user(): PostInformation | undefined {
    return this.postService.postInformation;
  }

  /* Sort method by date for posts */
  sortPostsByDate() {
    this.sortAscending = !this.sortAscending;
    this.posts$ = this.posts$.pipe(
      map(posts => posts.slice().sort((a, b) => {
        const dateA = a.createdAt ? new Date(a.createdAt).getTime() : 0;
        const dateB = b.createdAt ? new Date(b.createdAt).getTime() : 0;
        return this.sortAscending ? dateA - dateB : dateB - dateA;
      }))
    );
  }
}
