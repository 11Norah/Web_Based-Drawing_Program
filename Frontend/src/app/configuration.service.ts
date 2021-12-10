import { Injectable, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NONE_TYPE } from '@angular/compiler';
import { AppComponent } from './app.component';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  
 
  constructor(private http: HttpClient,) {
   }
  
  //sending coordinates when line /rectangle / circle/line  is called 
  send(type:string ,colour:string,x1:number,y1:number ,x2:number,y2:number){
    console.log(type, "the style is "+colour);
    this.http.get('http://localhost:8080/add',{
    params:{
      name :type,
      color:colour,
      x1 :x1 ,    
      y1 :y1 ,
      x2:x2,
      y2:y2,
      x3 : null,
      y3:null
    },
    observe:'response'
  }).subscribe(response=>{
    this.b=response.body!
  }
  )}
//sending coordinates when ellipse / triangle  is called 
send_et(type:string,colour:string,x1:number,y1:number,x2:number,y2:number,x3:number,y3:number){
  console.log(type);
  this.http.get('http://localhost:8080/add',{
  params:{
    name :type ,
    color  :colour,
    x1  :x1 ,
    y1:y1,
    x2:x2,
    y2:y2,
    x3:x3,
    y3:y3
   
  },
  observe:'response'
}).subscribe(response=>{
this.b=response.body!
});}
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
}
