package org.example.cuenta_service_devsu.cuenta.application.query;

import org.example.cuenta_service_devsu.cuenta.domain.repository.MovimientoRepo;
import org.example.cuenta_service_devsu.shared.application.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BuscarPorRangoFechas<T> implements Query<List<T>> {

    private MovimientoRepo repo;
    private Date data1;
    private Date data2;
    private int data3;

    private BuscarPorRangoFechas() {
    }

    @Override
    public List<T> execute() {
        return repo.buscarPorRangoFechas(data1, data2, data3);
    }


    public static final class BuscarPorRangoFechasBuilder {
        private MovimientoRepo repo;
        private Date data1;
        private Date data2;
        private int data3;

        private BuscarPorRangoFechasBuilder() {
        }

        public static BuscarPorRangoFechasBuilder aBuscarPorRangoFechas() {
            return new BuscarPorRangoFechasBuilder();
        }

        public BuscarPorRangoFechasBuilder withRepo(MovimientoRepo repo) {
            this.repo = repo;
            return this;
        }

        public BuscarPorRangoFechasBuilder withData1(Date data1) {
            this.data1 = data1;
            return this;
        }

        public BuscarPorRangoFechasBuilder withData2(Date data2) {
            this.data2 = data2;
            return this;
        }

        public BuscarPorRangoFechasBuilder withData3(int data3) {
            this.data3 = data3;
            return this;
        }

        public BuscarPorRangoFechas build() {
            BuscarPorRangoFechas buscarPorRangoFechas = new BuscarPorRangoFechas();
            buscarPorRangoFechas.data1 = this.data1;
            buscarPorRangoFechas.repo = this.repo;
            buscarPorRangoFechas.data2 = this.data2;
            buscarPorRangoFechas.data3 = this.data3;
            return buscarPorRangoFechas;
        }
    }
}
