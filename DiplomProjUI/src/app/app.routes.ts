import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { StudentsComponent } from './students/students.component';
import { TeachersComponent } from './teachers/teachers.component';
import { ApplicationsComponent } from './applications/applications.component';
import { ThesisComponent } from './thesis/thesis.component';
import { DefencesComponent } from './defences/defences.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'students', component: StudentsComponent},
    {path: 'teachers', component: TeachersComponent},
    {path: 'applications', component: ApplicationsComponent},
    {path: 'thesis', component: ThesisComponent},
    {path: 'defences', component: DefencesComponent},

];
