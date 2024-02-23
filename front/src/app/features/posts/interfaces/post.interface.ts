export interface Post {
  id?: number;
  title: string;
  author: string;
  content: string;
  date: Date;
  topicId: number;
  users: number[];
  createdAt?: Date;
  updatedAt?: Date;
}
