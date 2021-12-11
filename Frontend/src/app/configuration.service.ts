import { Injectable, Input } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { NONE_TYPE } from '@angular/compiler';
import { AppComponent } from './app.component';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {ResponseObject} from './responseObject';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  private appcompon:AppComponent

  constructor(private http: HttpClient,) {
   }
   b:any
   ya:any
   context:any
   ngOnInit():void {

         //this.context=this.canvas.nativeElement.getContext('2d');

}
/*load(shapes:{type:string,color:string,p1:{x,y},p2:{x,y},p3:{x:number,y:number}}[]){
  this.context=this.appcompon.context
  this.context.clearRect(0,0,1000,500)
  let i=0;
  let findp1,findp2,findp3;
  let findcolor,findtype;
  for( i=0;i<shapes.length-1;i++){
    findp1=shapes[i].p1,findp2=shapes[i].p2,findp3=shapes[i].p3,findcolor=shapes[i].color,findtype=shapes[i].type
    if(findtype=='rectangle'){
      this.context.strokeStyle=findcolor;
      this.context.strokeRect(findp1.x,findp1.y,findp2.x-findp1.x,findp2.y-findp1.y);
      console.log(shapes[i].color)
    }
    else if(findtype=='square'){
      this.context.strokeStyle=findcolor;
      this.context.strokeRect(findp1.x,findp1.y,findp2.x-findp1.x,findp2.x-findp1.x);
    }
    else if(findtype=='circle'){
      let radius=0
      radius=Math.sqrt(Math.pow((findp2.x-findp1.x),2)+Math.pow((findp2.y-findp1.y),2));
      this.context.beginPath();
      this.context.strokeStyle=findcolor
            this.context.arc(findp1.x,findp1.y,radius,0,2*Math.PI);
            this.context.stroke();
    }
    else if(findtype=='ellipse'){
      let radius1=0;
      let radius2=0;
      this.context.beginPath();
      radius1=Math.sqrt(Math.pow((shapes[i].p2.x-shapes[i].p1.x),2)+Math.pow((shapes[i].p2.y-shapes[i].p1.y),2));
      radius2=Math.sqrt(Math.pow((shapes[i].p3.x-shapes[i].p1.x),2)+Math.pow((shapes[i].p3.y-shapes[i].p1.y),2));
      console.log(radius1);
      console.log(radius2);
      this.context.beginPath();
          this.context.ellipse(shapes[i].p1.x,shapes[i].p1.y,radius1,radius2,Math.PI,0,2*Math.PI);
          this.context.stroke();
    }
    else if(findtype=='triangle'){
      this.context.beginPath();
      this.context.strokeStyle=findcolor
      this.context.moveTo(findp1.x,findp1.y);
      this.context.lineTo(findp2.x,findp2.y);
      this.context.lineTo(findp3.x,findp3.y);
      this.context.moveTo(findp3.x,findp3.y);
      this.context.lineTo(findp1.x,findp1.y);
      this.context.stroke();
    }
    else if(findtype='line'){
      this.context.beginPath();
      this.context.strokeStyle=findcolor;
      this.context.moveTo(findp1.X, findp1.Y);
    this.context.lineTo(findp2.X, findp2.Y);
    this.context.lineWidth = 2;
    this.context.stroke();
    this.context.closePath();
    }
  }
}*/
  //sending coordinates when line /rectangle / circle/line  is called
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
  select(x1:number,y1:number):string{
    this.http.get('http://localhost:8080/select',{
      responseType: 'text',
    params:{
      x:x1,
      y :y1
    },
    observe: 'response'

    }).subscribe(response =>{
      this.b=response.body
      })
      return "true";
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
  move_send(px1:number,py1:number ,px2:number ,py2:number){
    console.log("ana fe move");
      this.http.get('http://localhost:8080/move',{
     params:{
      x1:px1,
      y1:py1,
      x2:px2,
       y2:py2
  },
observe:'response'
}).subscribe(response =>{
      this.b=response.body!
      console.log(this.b )})
      return this.b
      }
    //to copy shape
    copy_send(p1:{x:number,y:number}){

      this.http.post('http://localhost:8080/copy',{
      params:{
       click :p1
      }})}
      //to resize shape
      resize_send(p1:{x:number,y:number}){

        this.http.post('http://localhost:8080/resize',{
        params:{
         click :p1
        }})}

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
}
