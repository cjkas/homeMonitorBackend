package cz.slaw.hm.repository.impl;

import cz.slaw.hm.domain.WeatherStationProbeEntity;
import cz.slaw.hm.repository.WeatherStationDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class WeatherStationDaoImpl implements WeatherStationDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<WeatherStationProbeEntity> findAll(int start) {

        return createCriteria()
                .addOrder(Order.asc("id"))
                .setFirstResult(start)
                .list();
    }

    @Override
    public WeatherStationProbeEntity getLast() {
        return (WeatherStationProbeEntity) createCriteria().setMaxResults(1).addOrder(Order.desc("id")).uniqueResult();
    }

    @Override
    public Long count() {
        return (Long) createCriteria()
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    private Criteria createCriteria() {
        Session session = getSession();
        return session.createCriteria(WeatherStationProbeEntity.class);
    }

    private Session getSession() {
        return (Session) entityManager.getDelegate();
    }


}
