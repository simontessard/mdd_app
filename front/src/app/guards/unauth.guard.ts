import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router";
import { PostService } from "../services/post.service";

@Injectable({providedIn: 'root'})
export class UnauthGuard implements CanActivate {

  constructor(
    private router: Router,
    private postService: PostService,
  ) {
  }

  public canActivate(): boolean {
    if (this.postService.isLogged) {
      this.router.navigate(['rentals']);
      return false;
    }
    return true;
  }
}
