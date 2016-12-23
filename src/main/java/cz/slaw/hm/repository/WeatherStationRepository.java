package cz.slaw.hm.repository;

import cz.slaw.hm.domain.WeatherStationProbeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherStationRepository extends JpaRepository<WeatherStationProbeEntity, Long> {

    @Query("SELECT e FROM WeatherStationProbeEntity e ORDER BY e.id")
    Page<WeatherStationProbeEntity> findAll(Pageable pageable);

    @Query("SELECT MAX(e.id) FROM WeatherStationProbeEntity e")
    Long getLast();

//	public List<WeatherStationProbeEntity> findAll() {
//		return sessionFactory.getCurrentSession().createQuery("FROM WeatherStationProbeEntity ORDER BY id").list();
//	}
//
//	public WeatherStationProbeEntity getLast() {
//		Query query = sessionFactory.getCurrentSession().createQuery("FROM WeatherStationProbeEntity ORDER BY id DESC)");
//		query.setMaxResults(1);
//		return (WeatherStationProbeEntity) query.uniqueResult();
//	}

}
