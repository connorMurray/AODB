import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  SharedModule,
  ButtonModule,
  DataTableModule,
  DialogModule,
  ToolbarModule,
  DropdownModule,
  SpinnerModule
} from 'primeng/primeng';

import {AppComponent} from './app.component';
import {DashboardComponent} from './components/dashboard/dashboard.component'

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent
  ],
  imports: [
    DropdownModule,
    SpinnerModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    SharedModule,
    DataTableModule,
    DialogModule,
    ButtonModule,
    ToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
