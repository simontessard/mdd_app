import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Topic } from '../../../../interfaces/topic.interface';
import { PostService } from '../../../../services/post.service';
import { TopicService } from '../../../../services/topic.service';
import { Post } from '../../interfaces/post.interface';
import { PostApiService } from '../../services/post-api.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  public post: Post | undefined;
  public teacher: Topic | undefined;

  public isParticipate = false;

  public postId: string;
  public userId: string;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private postService: PostService,
    private postApiService: PostApiService,
    private topicService: TopicService,
    private matSnackBar: MatSnackBar,
    private router: Router) {
    this.postId = this.route.snapshot.paramMap.get('id')!;
    this.userId = this.postService.postInformation!.id.toString();
  }

  public ngOnInit(): void {
    this.fetchPost();
  }

  public back() {
    window.history.back();
  }

  public delete(): void {
    this.postApiService
      .delete(this.postId)
      .subscribe((_: any) => {
          this.matSnackBar.open('Post deleted !', 'Close', { duration: 3000 });
          this.router.navigate(['posts']);
        }
      );
  }

  public participate(): void {
    this.postApiService.participate(this.postId, this.userId).subscribe(_ => this.fetchPost());
  }

  public unParticipate(): void {
    this.postApiService.unParticipate(this.postId, this.userId).subscribe(_ => this.fetchPost());
  }

  private fetchPost(): void {
    this.postApiService
      .detail(this.postId)
      .subscribe((post: Post) => {
        this.post = post;
        this.isParticipate = post.users.some(u => u === this.postService.postInformation!.id);
        this.topicService
          .detail(post.topicId.toString())
          .subscribe((teacher: Topic) => this.teacher = teacher);
      });
  }
}
