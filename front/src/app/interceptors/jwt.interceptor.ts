import { HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PostService } from '../services/post.service';

@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  constructor(private postService: PostService) {}

  public intercept(request: HttpRequest<any>, next: HttpHandler) {
    if (this.postService.isLogged) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.postService.postInformation!.token}`,
        },
      });
    }
    return next.handle(request);
  }
}
