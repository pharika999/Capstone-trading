package com.dbs.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Instrument;

public interface InstrumentRepository extends CrudRepository<Instrument, String>{

}
