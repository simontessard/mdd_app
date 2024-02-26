import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
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
  public postForm?: FormGroup;

  public postId: string;
  public userId: string;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private postService: PostService,
    private postApiService: PostApiService,
    private matSnackBar: MatSnackBar) {
    this.postId = this.route.snapshot.paramMap.get('id')!;
    this.userId = this.postService.postInformation!.id.toString();
    this.comments$ = of([]);
    this.postForm = this.fb.group({});
  }

  public ngOnInit(): void {
    this.fetchPost();
    this.getComments().subscribe(comments => this.comments$ = of(comments));
    this.initForm();
  }

  public back() {
    window.history.back();
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

  private initForm(post?: Post, topicId?: number): void {
    this.postForm = this.fb.group({
      comment: [
        post ? post.title : '',
        [Validators.required]
      ],
    });
  }

  public submit(): void {
    const comment = this.postForm!.get('comment')!.value;
    this.postApiService
      .addComment(this.postId, this.userId, comment)
      .subscribe(() => {
        this.matSnackBar.open('Comment added!', 'Close', { duration: 3000 });
        this.getComments().subscribe(comments => this.comments$ = of(comments));
        this.postForm!.get('comment')!.setValue('');
      });
  }
}
