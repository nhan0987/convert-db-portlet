create index IX_DD0929DE on expandotable (name);

create index IX_AA017738 on opencps_domain_target (targetDomain);

create index IX_537A60EE on opencps_employee_jobpos (employeeId, jobPosId);

create index IX_289196B0 on opencps_workingunit_jobpos (jobPosId, workingUnitId);

create index IX_4440D608 on users_orgs (organizationId, userId);

create index IX_A3D5AEAB on users_roles (roleId, userId);