import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from '../../../../services/post.service';
import { Post } from '../../interfaces/post.interface';
import { PostApiService } from '../../services/post-api.service';
import { Comment } from '../../interfaces/comment.interface';
import {Observable, of} from "rxjs";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  public post: Post | undefined;
  public comments$: Observable<Comment[]>;

  public postId: string;
  public userId: string;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private postService: PostService,
    private postApiService: PostApiService,
    private matSnackBar: MatSnackBar,
    private router: Router) {
    this.postId = this.route.snapshot.paramMap.get('id')!;
    this.userId = this.postService.postInformation!.id.toString();
    this.comments$ = of([]);
  }

  public ngOnInit(): void {
    this.fetchPost();
    this.getComments().subscribe(comments => this.comments$ = of(comments));
  }

  public back() {
    window.history.back();
  }

  public comment(): void {
    this.postApiService.addComment(this.postId, this.userId).subscribe(_ => this.fetchPost());
  }

  private fetchPost(): void {
    this.postApiService
      .detail(this.postId)
      .subscribe((post: Post) => {
        this.post = post;
      });
  }

  private getComments(): Observable<Comment[]> {
    return this.postApiService.getComments(this.postId.toString());
  }
}
