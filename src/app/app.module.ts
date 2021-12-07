import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SHAPESComponent } from './shapes/shapes.component';
//import { CanvasComponent } from './canvas.component';
//import { LineGraphComponent } from '../line-graph/line-graph.component';
@NgModule({
  declarations: [
    AppComponent,
    SHAPESComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
