import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from '../../interfaces/user.interface';
import { PostService } from '../../services/post.service';
import { UserService } from '../../services/user.service';
import {Subcription} from "../../interfaces/sub.interface";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public user: User | undefined;
  public subscriptions?: Subcription[] | undefined;

  constructor(private router: Router,
              private postService: PostService,
              private matSnackBar: MatSnackBar,
              private userService: UserService) {
  }

  public ngOnInit(): void {
    this.userService
      .getById(this.postService.postInformation!.id.toString())
      .subscribe((user: User) => this.user = user);
    this.userService
      .getSubscriptions(this.postService.postInformation!.id.toString())
      .subscribe((subs: Subcription[]) => this.subscriptions = subs);
  }

  public back(): void {
    window.history.back();
  }

  public unsubscribe(subscriptionId: number): void {
      this.userService
        .unsubscribe(this.postService.postInformation!.id, subscriptionId)
        .subscribe(
          () => {
            this.matSnackBar.open("You have successfully unsubscribed!", 'Close', { duration: 3000 });
            // Refresh subscriptions after successful unsubscription
            this.userService
              .getSubscriptions(this.postService.postInformation!.id.toString())
              .subscribe((subs: Subcription[]) => this.subscriptions = subs);
          },
          error => {
            this.matSnackBar.open("An error occurred while unsubscribing. Please try again.", 'Close', { duration: 3000 });
          }
        );
    }

  public delete(): void {
    this.userService
      .delete(this.postService.postInformation!.id.toString())
      .subscribe((_) => {
        this.matSnackBar.open("Your account has been deleted !", 'Close', { duration: 3000 });
        this.postService.logOut();
        this.router.navigate(['/']);
      })
  }
  public logout(): void {
    this.postService.logOut();
    this.router.navigate([''])
  }
}
