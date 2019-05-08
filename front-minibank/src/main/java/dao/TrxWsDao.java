package dao;

import dto.CommonResponse;
import entity.TrxEntity;
import entity.Vtrx;

import java.util.List;

public interface TrxWsDao {
    List<Vtrx> getTransactionReport(String cif);

    CommonResponse<TrxEntity> topUp(TrxEntity trxEntity);

    CommonResponse<TrxEntity> cashWithDrawal(TrxEntity trxEntity);

    CommonResponse<TrxEntity> transfer(TrxEntity trxEntity);
}
