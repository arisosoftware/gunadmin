package cn.enilu.guns.service;


public interface CrudService<T, ID>
		extends InsertService<T, ID>, UpdateService<T, ID>, DeleteService<ID>, SelectService<T, ID> {
}
