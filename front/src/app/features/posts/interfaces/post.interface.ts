import {Topic} from "../../../interfaces/topic.interface";

export interface Post {
  id?: number;
  title: string;
  author: string;
  content: string;
  date: Date;
  topicId: number;
  topic?: Topic;
  users: number[];
  createdAt?: Date;
  updatedAt?: Date;
}
