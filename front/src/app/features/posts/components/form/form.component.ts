import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from '../../../../services/post.service';
import { TopicService } from '../../../../services/topic.service';
import { Post } from '../../interfaces/post.interface';
import { PostApiService } from '../../services/post-api.service';
import {Topic} from "../../../../interfaces/topic.interface";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  public onUpdate: boolean = false;
  public postForm: FormGroup | undefined;
  public topics$ = this.topicService.all();
  private id: string | undefined;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private matSnackBar: MatSnackBar,
    private postApiService: PostApiService,
    private postService: PostService,
    private topicService: TopicService,
    private router: Router
  ) {
  }

  public ngOnInit(): void {
    const url = this.router.url;
    if (url.includes('update')) {
      this.onUpdate = true;
      this.id = this.route.snapshot.paramMap.get('id')!;
      this.postApiService
        .detail(this.id)
        .subscribe((post: Post) => this.initForm(post));
    } else {
      this.initForm();
    }
  }

  public submit(): void {
    const post = this.postForm?.value as Post;

    if (!this.onUpdate) {
      this.postApiService
        .create(post)
        .subscribe((_: Post) => this.exitPage('Post created !'));
    } else {
      this.postApiService
        .update(this.id!, post)
        .subscribe((_: Post) => this.exitPage('Post updated !'));
    }
  }

  private initForm(post?: Post, topicId?: number): void {
    this.postForm = this.fb.group({
      title: [
        post ? post.title : '',
        [Validators.required]
      ],
      topicId: [
        topicId ? topicId : '',
        [Validators.required]
      ],
      content: [
        post ? post.content : '',
        [
          Validators.required,
          Validators.max(2000)
        ]
      ],
    });
  }

  private exitPage(message: string): void {
    this.matSnackBar.open(message, 'Close', { duration: 3000 });
    this.router.navigate(['posts']);
  }
}