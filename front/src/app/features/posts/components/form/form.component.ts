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

  public postForm: FormGroup | undefined;
  public topics$ = this.topicService.all();

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private matSnackBar: MatSnackBar,
    private postApiService: PostApiService,
    private topicService: TopicService,
    private router: Router
  ) {
  }

  public ngOnInit(): void {
    this.initForm();
  }

  public submit(): void {
    const post = this.postForm?.value as Post;

    this.postApiService
      .create(post)
      .subscribe((_: Post) => this.exitPage('Post created!'));
  }

  private initForm(): void {
    this.postForm = this.fb.group({
      title: ['',
        [Validators.required]
      ],
      topicId: ['',
        [Validators.required]
      ],
      content: ['',
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
