import { Tab } from '@toss/tds-mobile';

interface Props {
  selected: number;
  onChange: (index: number) => void;
}

const DashboardTabs = ({ selected, onChange }: Props) => {
  return (
    <Tab size="large" onChange={onChange}>
      <Tab.Item selected={selected === 0}>신고 관리</Tab.Item>
      <Tab.Item selected={selected === 1}>푸드트럭 목록</Tab.Item>
    </Tab>
  );
};

export default DashboardTabs;