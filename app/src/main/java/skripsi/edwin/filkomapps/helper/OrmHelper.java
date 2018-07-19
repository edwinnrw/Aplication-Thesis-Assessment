package skripsi.edwin.filkomapps.helper;

import java.util.List;

import skripsi.edwin.filkomapps.model.orm.RataRataKomponen;

public class OrmHelper {
    public static List<RataRataKomponen> getRataRataKomponenPembimbing(String peran) {
//        return SQLite.select(
//                DataKomponen_Table.id.withTable(NameAlias.builder("k").build()).as(RataRataKomponen.idName),
//                DataKomponen_Table.komponen.withTable(NameAlias.builder("k").build()).as(RataRataKomponen.komponenName),
//                Method.avg(Nilai_Table.nilai.withTable(NameAlias.builder("i").build())).as(RataRataKomponen.avgName))
//                .from(DataKomponen.class).as("k")
//                .innerJoin(Nilai.class).as("i")
//                .on(Nilai_Table.komponenId.withTable(NameAlias.builder("i").build())
//                        .eq(DataKomponen_Table.id.withTable(NameAlias.builder("k").build())))
//                .innerJoin(Majelis.class).as("m")
//                .on(Majelis_Table.id.withTable(NameAlias.builder("m").build())
//                        .eq(Nilai_Table.idDosen.withTable(NameAlias.builder("i").build())))
//                .where(Majelis_Table.peran.withTable(NameAlias.builder("m").build()).eq(peran))
//                .groupBy(DataKomponen_Table.komponen.withTable(NameAlias.builder("k").build()))
//                .queryCustomList(RataRataKomponen.class);
        return null;
    }

    public static List<RataRataKomponen> getRataRataKomponenPenulisan(String peran) {
//        return SQLite.select(
//                DataKomponen_Table.id.withTable(NameAlias.builder("k").build()).as(RataRataKomponen.idName),
//                DataKomponen_Table.komponen.withTable(NameAlias.builder("k").build()).as(RataRataKomponen.komponenName),
//                Method.avg(Nilai_Table.nilai.withTable(NameAlias.builder("i").build())).as(RataRataKomponen.avgName))
//                .from(DataKomponen.class).as("k")
//                .innerJoin(Nilai.class).as("i")
//                .on(Nilai_Table.komponenId.withTable(NameAlias.builder("i").build())
//                        .eq(DataKomponen_Table.id.withTable(NameAlias.builder("k").build())))
//                .innerJoin(Majelis.class).as("m")
//                .on(Majelis_Table.id.withTable(NameAlias.builder("m").build())
//                        .eq(Nilai_Table.idDosen.withTable(NameAlias.builder("i").build())))
//                .where(Majelis_Table.peran.withTable(NameAlias.builder("m").build()).eq(peran))
//                .and(DataKomponen_Table.id.withTable(NameAlias.builder("k").build()).eq("2"))
//                .groupBy(DataKomponen_Table.komponen.withTable(NameAlias.builder("k").build()))
//                .queryCustomList(RataRataKomponen.class);
        return null;
    }

    public static List<RataRataKomponen> getRataRataKomponenPenguji(String peran) {
//        List<RataRataKomponen> rataRataKomponens = SQLite.select(
//                DataKomponen_Table.id.withTable(NameAlias.builder("k").build()).as(RataRataKomponen.idName),
//                DataKomponen_Table.komponen.withTable(NameAlias.builder("k").build()).as(RataRataKomponen.komponenName),
//                Method.avg(Nilai_Table.nilai.withTable(NameAlias.builder("i").build())).as(RataRataKomponen.avgName))
//                .from(DataKomponen.class).as("k")
//                .innerJoin(Nilai.class).as("i")
//                .on(Nilai_Table.komponenId.withTable(NameAlias.builder("i").build())
//                        .eq(DataKomponen_Table.id.withTable(NameAlias.builder("k").build())))
//                .innerJoin(Majelis.class).as("m")
//                .on(Majelis_Table.id.withTable(NameAlias.builder("m").build())
//                        .eq(Nilai_Table.idDosen.withTable(NameAlias.builder("i").build())))
//                .where(Majelis_Table.peran.withTable(NameAlias.builder("m").build()).eq(peran))
//                .and(DataKomponen_Table.id.withTable(NameAlias.builder("k").build()).notEq("1"))
//                .and(DataKomponen_Table.id.withTable(NameAlias.builder("k").build()).notEq("6"))
//                .groupBy(DataKomponen_Table.komponen.withTable(NameAlias.builder("k").build()))
//                .queryCustomList(RataRataKomponen.class);
//        return rataRataKomponens;
        return null;
    }

    public static List<RataRataKomponen> getRataRataNilaiPublikasi(String peran) {
//        return SQLite.select(
//                DataKomponen_Table.id.withTable(NameAlias.builder("k").build()).as(RataRataKomponen.idName),
//                DataKomponen_Table.komponen.withTable(NameAlias.builder("k").build()).as(RataRataKomponen.komponenName),
//                Method.avg(Nilai_Table.nilai.withTable(NameAlias.builder("i").build())).as(RataRataKomponen.avgName))
//                .from(DataKomponen.class).as("k")
//                .innerJoin(Nilai.class).as("i")
//                .on(Nilai_Table.komponenId.withTable(NameAlias.builder("i").build())
//                        .eq(DataKomponen_Table.id.withTable(NameAlias.builder("k").build())))
//                .innerJoin(Majelis.class).as("m")
//                .on(Majelis_Table.id.withTable(NameAlias.builder("m").build())
//                        .eq(Nilai_Table.idDosen.withTable(NameAlias.builder("i").build())))
//                .where(Majelis_Table.peran.withTable(NameAlias.builder("m").build()).eq(peran))
//                .and(DataKomponen_Table.id.withTable(NameAlias.builder("k").build()).eq("6"))
//                .queryCustomList(RataRataKomponen.class);
        return null;
    }
}
