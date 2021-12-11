import { Injectable, Input } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { NONE_TYPE } from '@angular/compiler';
import { AppComponent } from './app.component';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {ResponseObject} from './responseObject';

@Injectable({
  providedIn: 'root',
})
export class ConfigurationService {
  private appcompon: AppComponent;

  constructor(private http: HttpClient) {}
  b: any;
  ya: any;
  context: any;
  ngOnInit(): void {
    //this.context=this.canvas.nativeElement.getContext('2d');
  }

  send(type:string ,colour:string,x1:number,y1:number ,x2:number,y2:number){
    console.log(type, "the style is "+colour);
    let shape = {
      name: type,
      color: colour,
      x1: x1,
      y1: y1,
      x2: x2,
      y2: y2,
    }
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    console.log(JSON.stringify(shape));
    this.http.post('http://localhost:8080/add',JSON.stringify(shape), {headers: headers})
    .subscribe(res => {
      console.log(res);
    })
  }
  select(x1: number, y1: number): any {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get(`http://localhost:8080/select/${x1}/${y1}`, { responseType: 'text' })
      .subscribe(res => console.log(res));
  }

  //sending coordinates when ellipse / triangle  is called
  send_et(type:string,colour:string,x1:number,y1:number,x2:number,y2:number,x3:number,y3:number){
    console.log(type);
    let shape = {
      name: type,
      color: colour,
      x1: x1,
      y1: y1,
      x2: x2,
      y2: y2,
      x3: x3,
      y3: y3
    }
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    console.log(JSON.stringify(shape));
    this.http.post('http://localhost:8080/add',JSON.stringify(shape), {headers: headers})
    .subscribe(res => {
      console.log(res);
    })
  }
  //to send new move action
  move_send(px: number, py: number): any {
    console.log('ana fe move');
    console.log(this.b);
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const params = new HttpParams()
      .append('x', px)
      .append('y', py);
    return this.http.get<ResponseObject[]>('http://localhost:8080/move', {params, headers})
      .subscribe(res => console.log(res));
  }
  //to copy shape
  copy_send(px1: number, py1: number): any {
    console.log('ana fe copy');
    console.log(this.b);
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const params = new HttpParams()
      .append('x', px1)
      .append('y', py1);
    return this.http.get('http://localhost:8080/copy', {params, headers})
      .subscribe(res => console.log(res));
  }
  delete_send(): any {
    console.log('ana fe delete');
    console.log(this.b);
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.get('http://localhost:8080/delete', {headers})
      .subscribe(res => console.log(res));
  }
  //to resize shape
  resize_send(px1: number, py1: number,px2:number,py2:number): any {
    console.log('ana fe copy');
    console.log(this.b);
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const params = new HttpParams()
      .append('x1', px1)
      .append('y1', py1)
      .append('x2', px2)
      .append('y2', py2);
    return this.http.get('http://localhost:8080/resize', {params, headers})
      .subscribe(res => console.log(res));
  }
  save_send(filePath: string, fileType: string) {
    let fileInfo = {
      filePath: "E://Paint/Backend/TEST",
      fileType: "xml",
    }
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    this.http.post('http://localhost:8080/save', JSON.stringify(fileInfo), {headers})
    .subscribe(res => {
      console.log(res);
    });
  }

  load_send(filePath: string, fileType: string) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const params = new HttpParams()
      .append('filePath', 'E://Paint/Backend/TEST')
      .append('fileType', 'xml');
    this.http.get<ResponseObject[]>('http://localhost:8080/load', {headers, params})
    .subscribe(res => console.log(res));
  }

  clear_send() {
    this.http.get('http://localhost:8080/clear')
      .subscribe();
  }
}
