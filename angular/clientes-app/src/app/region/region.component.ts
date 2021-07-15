import { Component, OnInit } from '@angular/core';
import { Region } from './region';
import { RegionService } from './region.service';

@Component({
  selector: 'app-region',
  templateUrl: './region.component.html',
  styleUrls: ['./region.component.css']
})
export class RegionComponent implements OnInit {

  regiones: Region[];

  constructor(private regionService: RegionService) { }

  ngOnInit(): void {
    this.regionService.getRegiones().subscribe(
      regiones => this.regiones = regiones
    );
  }

}
