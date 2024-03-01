import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from '../../interfaces/user.interface';
import { PostService } from '../../services/post.service';
import { UserService } from '../../services/user.service';
import {Subcription} from "../../interfaces/sub.interface";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Post} from "../../features/posts/interfaces/post.interface";
import {updateMe} from "../interfaces/updateMe.interface";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public user: User | undefined;
  public subscriptions?: Subcription[] | undefined;
  public infoForm: FormGroup;

  constructor(private router: Router,
              private postService: PostService,
              private matSnackBar: MatSnackBar,
              private fb: FormBuilder,
              private userService: UserService) {
    this.infoForm = this.fb.group({});
  }

  public ngOnInit(): void {
    this.userService
        .getById(this.postService.postInformation!.id.toString())
        .subscribe((user: User) => {
          this.user = user;
          this.infoForm  = this.fb.group({
            name: [this.user ? this.user.name : '',
              [Validators.required,
                Validators.minLength(3)]
            ],
            email: [this.user ? this.user.email : '',
            [
              Validators.required,
              Validators.maxLength(2000)
            ]
        ]
        })
      });
    this.userService
      .getSubscriptions(this.postService.postInformation!.id.toString())
      .subscribe((subs: Subcription[]) => this.subscriptions = subs);
  }

  public submitUpdateProfile(): void {
    const updatedMe = this.infoForm?.value as updateMe;

    this.userService.updateProfile(this.postService.postInformation!.id, updatedMe).subscribe(response => {
      this.matSnackBar.open(response.message, 'Close', { duration: 4000 });
    });
    this.logout();
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

  public logout(): void {
    this.postService.logOut();
    this.router.navigate([''])
  }
}
