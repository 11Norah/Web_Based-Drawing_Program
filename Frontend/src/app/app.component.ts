import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {HttpClient ,HttpHeaders} from '@angular/common/http';
import {  SHAPES, SHAPESComponent } from './shapes/shapes.component';
import { ConfigurationService } from './configuration.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  title = 'paint';

  @ViewChild('canvas',{static:true})

  canvas: ElementRef<HTMLCanvasElement> ;
  context: CanvasRenderingContext2D ;
  res: any;
  private hhtp: HttpClient;

  constructor( private ShapeService :ConfigurationService ){}

  ngOnInit():void {
    this.context=this.canvas.nativeElement.getContext('2d');
  }
rectangle():void{

  const shape_rect=new SHAPES(this.context,this.ShapeService);
  shape_rect.rectangle();
};

circle():void{
  const shape_circle= new SHAPES(this.context,this.ShapeService);
  shape_circle.circle();
}
square():void{

  const shape_square=new SHAPES(this.context,this.ShapeService);

  shape_square.square();

};

  clearCanvas() {
    var m = confirm("Want to clear");
    var x,y;
    if (m) {
      this.context.clearRect(0, 0, this.context.canvas.width, this.context.canvas.height);
      this.ShapeService.clear_send();
      onmousedown=(event:MouseEvent)=>{
        x=event.offsetX;
        y=event.offsetY;
      }
    }
      }

      line(){
        const new_line=new SHAPES(this.context,this.ShapeService);

        new_line.drawLine();

      }
      ellipse():void{
       const shape_ellipse= new SHAPES(this.context,this.ShapeService);

       shape_ellipse.ellipse();
     }
     triangle():void{
       const shape_triangle=new SHAPES(this.context,this.ShapeService);

       shape_triangle.triangle();
     }

     private  data:any;
     move():void{
       var m = confirm("Choose shape then the new point");
       if(m){
         let selectx=0;
         let selecty=0;
         let newx=0;
         let newy=0;
         let a=0;
         let b=0;
         onmousedown=(event:MouseEvent)=>{
           selectx=event.offsetX;
           selecty=event.offsetY;
           console.log(selectx,selecty)
           let validshape:string;
           this.ShapeService.select(selectx,selecty)
           .subscribe(res => {
             validshape = res;
             if(validshape!=="null"){
               onmousedown=(event:MouseEvent)=>{
                 newx=event.offsetX;
                 newy=event.offsetY;
                 console.log(newx,newy)
                 this.ShapeService.move_send(newx, newy)
                  .subscribe(newRes => {
                    this.data = newRes;
                    console.log('data is now set', this.data);
                    this.load(this.data);
                  })
               }
              }
           })
          }


        }
       }
  copy(){
    let selectx=0;
    let selecty=0;
    let newx=0;
    let newy=0;
    onmousedown=(event:MouseEvent)=>{
      selectx=event.offsetX;
      selecty=event.offsetY;
      console.log(selectx,selecty)
      this.ShapeService.select(selectx, selecty)
        .subscribe(res => {
          if(res !== "null"){
            onmousedown=(event:MouseEvent)=>{
              newx=event.offsetX;
              newy=event.offsetY;
              console.log(newx, newy)
              this.ShapeService.copy_send(newx, newy)
                .subscribe(newRes => {
                  this.load(newRes);
                })
            }
          }
        })
      }
  }
  resize(){
    let selectx=0;
    let selecty=0;
    let newx=0;
    let newy=0;
    onmousedown=(event:MouseEvent)=>{
      selectx=event.offsetX;
      selecty=event.offsetY;
      console.log(selectx,selecty)
      this.ShapeService.select(selectx, selecty)
        .subscribe(res => {
          if(res !== "null") {
            let newX1 = 0, newY1 = 0, newX2 = 0, newY2 = 0;
            onmousedown=(event:MouseEvent)=>{
              newX1=event.offsetX;
              newY1=event.offsetY;
              console.log(newX1,newY1);
              if(res === "triangle" || res === "ellipse") {
                onmousedown=(event: MouseEvent) => {
                  newX2 = event.offsetX;
                  newY2 = event.offsetY;
                  this.ShapeService.resize_send(newX1, newY1, newX2, newY2)
                    .subscribe(newRes => {
                      console.log(newRes);
                      this.load(newRes);
                    });
                }
              }
              else {
                this.ShapeService.resize_send(newX1, newY1, 0, 0)
                  .subscribe(newRes => {
                    console.log(newRes);
                    this.load(newRes);
                  });
              }
            }
          }
        })
    }
  }

  Load(x:any){
    console.log(x);
    let n=x.length;
    let type;
    console.log(n);
     if((x[n-1]=='l')&&(x[n-2]=='m')&&(x[n-3]=='x')) {
       type='xml';
       this.ShapeService.load_send(x,type)
          .subscribe(loadedShapes => this.load(loadedShapes));
     }
    else if((x[n-1]=='n')&&(x[n-2]=='o')&&(x[n-3]=='s')&&(x[n-4]=='j')) {
      type='json';
      this.ShapeService.load_send(x,type)
        .subscribe(loadedShapes => this.load(loadedShapes));
    }
    else {
      alert("file type is not JSON or XML")
    }
     console.log(type);
  }

  save(path:any, type:any){
    console.log(typeof(type));
    type=type.toString();
    console.log(path ,type);
    console.log(typeof(type));
    this.ShapeService.save_send(path+"."+type, type);
  }

  load(shapes:{name:string,color:string,x1:number,y1:number,x2:number,y2:number,x3:number,y3:number}[]){
    let i=0;
    let findx1,findx2,findx3;
    let findy1,findy2,findy3;
    let findcolor,findtype;
    this.context.clearRect(0,0,1000,500)
    for( i=0;i<shapes.length;i++){
      findx1=shapes[i].x1,findx2=shapes[i].x2,findx3=shapes[i].x3,findcolor=shapes[i].color,findtype=shapes[i].name
      findy1=shapes[i].y1,findy2=shapes[i].y2,findy3=shapes[i].y3
      if(findtype=='rectangle'){
        this.context.strokeStyle=findcolor;
        this.context.strokeRect(findx1,findy1,findx2-findx1,findy2-findy1);
        console.log(shapes[i].color)
      }
      else if(findtype=='square'){
        this.context.strokeStyle=findcolor;
        this.context.strokeRect(findx1,findy1,findx2-findx1,findx2-findx1.x);
      }
      else if(findtype=='circle'){
        let radius=0
        radius=Math.sqrt(Math.pow((findx2-findx1),2)+Math.pow((findy2-findy1),2));
        this.context.beginPath();
        this.context.strokeStyle=findcolor
              this.context.arc(findx1,findy1,radius,0,2*Math.PI);
              this.context.stroke();
      }
      else if(findtype=='ellipse'){
        let radius1=0;
        let radius2=0;
        this.context.beginPath();
        radius1=Math.sqrt(Math.pow((shapes[i].x2-shapes[i].x1),2)+Math.pow((shapes[i].y2-shapes[i].y1),2));
        radius2=Math.sqrt(Math.pow((shapes[i].x3-shapes[i].x1),2)+Math.pow((shapes[i].y3-shapes[i].y1),2));
        console.log(radius1);
        console.log(radius2);
        this.context.beginPath();
            this.context.ellipse(shapes[i].x1,shapes[i].y1,radius1,radius2,Math.PI,0,2*Math.PI);
            this.context.stroke();
      }
      else if(findtype=='triangle'){
        this.context.beginPath();
        this.context.strokeStyle=findcolor
        this.context.moveTo(findx1,findy1);
        this.context.lineTo(findx2,findy2);
        this.context.lineTo(findx3,findy3);
        this.context.moveTo(findx3,findy3);
        this.context.lineTo(findx1,findy1);
        this.context.stroke();
      }
      else if(findtype='line'){
        this.context.beginPath();
        this.context.strokeStyle=findcolor;
        this.context.moveTo(findx1, findy1);
      this.context.lineTo(findx2, findy2);
      this.context.lineWidth = 2;
      this.context.stroke();
      this.context.closePath();
      }
    }
  }
  color(obj) {
    var x,y;
    this.context.strokeStyle=obj;
    onmousedown=(event:MouseEvent)=>{
      x=event.offsetX;
      y=event.offsetY;
    }
}}
