import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { ModalService } from './detalle/modal.service';
import swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];
  paginador: any;
  clienteSeleccionado: Cliente;

  constructor(private clienteService: ClienteService,
              private activatedRoute: ActivatedRoute,
              private modalService: ModalService, ) { }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe( params => {
      let page: number = +params.get('page')!; 

      if (page == null) {
        page = 0;
      }

      this.clienteService.getClientes(page).subscribe(response => {
        this.clientes = response.content as Cliente[]
        this.paginador = response;
      });
    });

    this.modalService.notificarUpload.subscribe(cliente => {
      this.clientes = this.clientes.map(clienteOriginal => {
        if(cliente.id == clienteOriginal.id) {
          clienteOriginal.foto = cliente.foto;
        }
        return clienteOriginal;
      })
    });

  }

  delete(cliente: Cliente): void {
    const swalWithBootstrapButtons = swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
      title: 'Estás seguro?',
      text: `Seguro que desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, Eliminar!',
      cancelButtonText: 'No, cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.clienteService.delete(cliente.id).subscribe(
          response => {
            this.clientes = this.clientes.filter(cli => cli !== cliente)
            swalWithBootstrapButtons.fire(
              'Cliente eliminado!',
              `Cliente ${cliente.nombre} eliminado con éxito`,
              'success'
            )
          }
        )
      }
    })
  }

  abrirModal(cliente: Cliente){
    this.clienteSeleccionado = cliente;
    this.modalService.abrirModal();
  }

}
