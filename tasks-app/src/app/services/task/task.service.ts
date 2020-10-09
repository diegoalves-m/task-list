import { Task } from './../../shared/models/task.model';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  
  url = environment.domain;
  httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" })
  };

  constructor(private http: HttpClient) { }


  async register(task: Task): Promise<any> {
    return await this.http.post<Task>(this.url + "tasks", JSON.stringify(task), this.httpOptions)
      .toPromise();
  }

  async update(task: Task): Promise<any> {
    return await this.http.put<Task>(this.url + "tasks/" + task.id, JSON.stringify(task), this.httpOptions)
      .toPromise();
  }

  async listAll() {
    return await this.http.get<Task[]>(this.url + 'tasks', this.httpOptions)
      .toPromise();
  }

  async findById(id: number): Promise<any> {
    return await this.http.get<Task>(this.url + "tasks/" + id, this.httpOptions)
      .toPromise();
  }
}
