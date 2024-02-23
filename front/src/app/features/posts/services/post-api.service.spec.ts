import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';

import { PostApiService } from './post-api.service';

describe('PostsService', () => {
  let service: PostApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientModule
      ]
    });
    service = TestBed.inject(PostApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
